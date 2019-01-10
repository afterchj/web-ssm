-- SELECT date_sub('2019-01-01',interval 12 month);
-- 支出分类
-- SELECT
-- 	*
-- FROM
-- 	alipay_record
-- WHERE
-- 	trade_type = '支出'
-- AND date_format(create_time, '%Y-%m') = date_format(
-- 	DATE_SUB(curdate(), INTERVAL 1 MONTH),
-- 	'%Y-%m'
-- ) GROUP BY counterparty;
-- 统计2018年每个月支出分类
SELECT
	trade_type,
	date_format(create_time, '%Y-%m') mm,
	FORMAT(SUM(money),2) perm
FROM
	alipay_record
WHERE
	create_time BETWEEN date_sub('2019-01-01',interval 12 month) AND '2019-01-01'
AND trade_type in('收入','支出') GROUP BY trade_type,mm;

-- 统计12月收入分类
select 
	create_time,
	trade_type,
	counterparty,
	shop_name,
	money,
	trade_status,
	notes,
	date_format(create_time, '%Y-%m') mm,
	FORMAT(SUM(money),2)
from alipay_record where trade_type='收入' and create_time between date_sub(now(),interval 12 month) and now() GROUP BY mm; 
-- 统计12月支出分类
select 
	create_time,
	trade_type,
	counterparty,
	shop_name,
	money,
	trade_status,
	notes,
	date_format(create_time, '%Y-%m') mm,
	FORMAT(SUM(money),2)
from alipay_record where trade_type='支出' and create_time between date_sub(now(),interval 12 month) and now() GROUP BY mm; 
-- 统计每一年的数据
SELECT
	DATE_FORMAT(create_time, '%Y') perYear,
	trade_type,
	FORMAT(SUM(money), 2) total
FROM
	alipay_record
WHERE
-- 	YEAR (create_time) = YEAR (date_sub(now(), INTERVAL 1 YEAR))
create_time >= DATE_SUB('2019-01-01', INTERVAL 3 YEAR) 
AND trade_type in ('支出','收入')
GROUP BY
	perYear,trade_type;







