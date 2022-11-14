package platform.szxyzxx.dto.seewo;

/**
 * 将未同步的数据发送（同步）到seewo的发送器
 */
public interface SeewoDataSender {
   /**
    * 将未同步的数据同步到Seewo，并且更新状态（相同的数据，同步一次后下次就不再同步）
    */
   void sendNotSynchronizedDataAndUpdate();
}
