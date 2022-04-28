commitLog
消息存储文件
文件大小1G，消息顺序存储
文件名为 0000(20位，不足左边补零) ，00001024XXX
文件位置：
${home}/store/commitlog/xxxxxx


comsumeQueue
消息索引存储文件
固定大小5.72M,存30W个条目，每条20位。写满后写下一个文件
8(消息在commitLog中偏移量)+4（消息长度）+8（tag的hash值）
文件名：00000000（20位）
文件存储位置  ${home}/store/comsumequeue/{topic}/{queueId}/00000000000000000


indexFile
   消息索引文件，提供了一种可以通过key或时间区间来查询消息的方法
   文件路径为  ${home}/store/index${fileName},文件名为创建时间的时间戳
  文件大小固定：400M
  
  
消息查找

topic/queueId/offset
