--Cho sẵn dữ liệu như sau:
SET NOCOUNT ON;
SET DATEFORMAT DMY;

-- Bảng dữ liệu bán hàng
IF OBJECT_ID('TempDb..#CTBH') IS NOT NULL DROP TABLE #CTBH;
CREATE TABLE #CTBH 
(	NGAY_CT DATETIME,
	SO_LUONG NUMERIC(18, 3),
	SO_TIEN DECIMAL(25, 0)
);

DECLARE @_I INT, @_SO_LUONG NUMERIC(18, 3), @_SO_TIEN NUMERIC(18, 0)
SET @_I = 1

WHILE @_I <= 100
BEGIN
	INSERT INTO #CTBH(NGAY_CT, SO_LUONG, SO_TIEN)
		SELECT (GETDATE() - RAND() * 777) AS Ngay_Ct, ROUND(RAND() * 888, 3), ROUND(RAND() * 9999, 0)
	
	SET @_I = @_I + 1
END
--Select * from #CTBH 
Select CONVERT(varchar,NGAY_CT,103) As ngay,Tien into #bt1 from #CTBH
Select * from #bt1
Select * from #bt1 where ngay between '21/09/2017' and '05/06/2019'
--SELECT * FROM #CTBH
/* Yêu cầu:
1/ Viết ra query trả về dữ liệu như sau:
	+ Nhóm tiền từng tháng theo từng dòng (Không yêu cầu có các dòng chi tiết)
	+ Với những tháng không có dữ liệu phát sinh, phải thêm dòng cho tháng đó với Tiền = 0
	+ Có tham số lựa chọn sẽ lên dữ liệu bao nhiêu tháng kể từ tháng hiện tại trở về trước. Tháng đầu tiên sẽ lấy dữ liệu từ ngày đầu tháng đó.
		Ví dụ: Ngày hiện tại là  06/08/2011.
			Truyền vào @_THANG = 12 thì sẽ lấy dữ liệu từ 01/09/2010 --> 06/08/2011
			-- 
	|Tháng            Tien				
	|---------------- ----------------	
	|09/2010          1234
	|10/2010          1234
	|11/2010          0
	|12/2010          1234
	|01/2011          0
	|02/2011          1234
	|03/2011          1234
	|04/2011          1234
	|05/2011          1234
	|06/2011          1234
	|07/2011          0
	|08/2011          1234
	|---------------- ---------------- 

2/ Cách xử lý như trên nhưng trả về bảng theo từng cột

	|T092010	|T102010	|T112010	|	.... ...|T072011	|T082011 |
	|--------------------------------------------------------------------|
	|1234		|1234		|0			|			|0			|1234	 |
	|--------------------------------------------------------------------|

--------------------------------------------------------------------- */