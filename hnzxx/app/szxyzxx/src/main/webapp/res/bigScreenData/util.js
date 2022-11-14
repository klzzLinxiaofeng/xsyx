const formatTime = time => {
  const date = new Date(time)
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()
  return {
    day: [year, month, day].map(formatNumber).join('-'),
    currentDay: [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':'),
    time: [hour, minute, second].map(formatNumber).join(':')
  }
}
const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}
function getBeforeDate(days){
    var now=new Date().getTime();
        var ago=now-86400000*days;//一天的毫秒数为86400000
        var agoData= new Date(ago);
        var year = agoData.getFullYear();
        var mon = agoData.getMonth() + 1;
        var day = agoData.getDate();
        mon=mon<10? '0'+mon:mon;
        day=day<10? '0'+day:day;
        var date=year+'-'+mon+'-'+day;
    return date;
};