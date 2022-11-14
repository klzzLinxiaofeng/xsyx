package com.xunyunedu.repair.pojo.model;

import com.xunyunedu.repair.pojo.RepairPojo;

import java.util.List;

public class RepairModel extends RepairPojo {

    List<RepairPojo> repairAll;

    public List<RepairPojo> getRepairAll() {
        return repairAll;
    }

    public void setRepairAll(List<RepairPojo> repairAll) {
        this.repairAll = repairAll;
    }
}
