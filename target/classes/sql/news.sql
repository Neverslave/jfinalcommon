#sql("getNewsLists")
select  * from newslist  order by createTime desc Limit ?;
#end