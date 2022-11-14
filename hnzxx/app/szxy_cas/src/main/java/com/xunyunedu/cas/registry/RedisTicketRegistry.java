package com.xunyunedu.cas.registry;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.jasig.cas.ticket.ServiceTicket;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.AbstractDistributedTicketRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTicketRegistry extends AbstractDistributedTicketRegistry {

	@Resource
	private RedisTemplate<String, Object> reidsTemplate;

	private Logger logger = LoggerFactory.getLogger(RedisTicketRegistry.class);

	private int tgtTimeout;

	private int stTimeout;

	public RedisTicketRegistry() {
	}

	public RedisTicketRegistry(RedisTemplate<String, Object> reidsTemplate,
			int tgtTimeout, int stTimeout) {
		super();
		this.reidsTemplate = reidsTemplate;
		this.tgtTimeout = tgtTimeout;
		this.stTimeout = stTimeout;
	}

	@Override
	public void addTicket(Ticket ticket) {
		logger.debug("Adding ticket {}", ticket);
		try {
			reidsTemplate.opsForValue().set(ticket.getId(), ticket,
					getTimeout(ticket), TimeUnit.SECONDS);
		} catch (final Exception e) {
			logger.error("Failed adding {}", ticket, e);
		}
	}

	@Override
	public boolean deleteTicket(String ticketId) {
		logger.debug("Deleting ticket {}", ticketId);
		try {
			this.reidsTemplate.delete(ticketId);
			return true;
		} catch (final Exception e) {
			logger.error("Failed deleting {}", ticketId, e);
		}
		return false;
	}

	@Override
	public Ticket getTicket(String ticketId) {
		try {
			final Ticket t = (Ticket) this.reidsTemplate.opsForValue().get(
					ticketId);
			if (t != null) {
				return getProxiedTicketInstance(t);
			}
		} catch (final Exception e) {
			logger.error("Failed fetching {} ", ticketId, e);
		}
		return null;
	}

	@Override
	public Collection<Ticket> getTickets() {
		throw new UnsupportedOperationException("GetTickets not supported.");
	}

	@Override
	protected boolean needsCallback() {
		return true;
	}

	@Override
	protected void updateTicket(Ticket ticket) {
		logger.debug("Updating ticket {}", ticket);
		try {
			this.reidsTemplate.delete(ticket.getId());
			reidsTemplate.opsForValue().set(ticket.getId(), ticket,
					getTimeout(ticket), TimeUnit.SECONDS);
		} catch (final Exception e) {
			logger.error("Failed updating {}", ticket, e);
		}
	}

	private int getTimeout(final Ticket t) {
		if (t instanceof TicketGrantingTicket) {
			return this.tgtTimeout;
		} else if (t instanceof ServiceTicket) {
			return this.stTimeout;
		}
		throw new IllegalArgumentException("Invalid ticket type");
	}

}
