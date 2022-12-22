-- Cho dữ liệu có sẵn như sau:
SET NOCOUNT ON;
IF OBJECT_ID('TempDb..#CTTEMP') IS NOT NULL DROP TABLE #CTTEMP;
CREATE TABLE #CTTEMP 
	(
		NGAY_CT		Smalldatetime	NOT NULL DEFAULT '',
		SO_CT		NVARCHAR(20)	NOT NULL DEFAULT '',
		DIEN_GIAI	NVARCHAR(254)	NOT NULL DEFAULT '',
		TK_NO		NVARCHAR(16)	NOT NULL DEFAULT '',
		TK_CO		NVARCHAR(16)	NOT NULL DEFAULT '',
		SO_TIEN		DECIMAL(25, 4)	NOT NULL DEFAULT 0,
		MA_PB		NVARCHAR(25)	NOT NULL DEFAULT '',
	)

INSERT INTO #CTTEMP (NGAY_CT, SO_CT, DIEN_GIAI, TK_NO, TK_CO, SO_TIEN, MA_PB)
SELECT '20120802', '0001', N'Chứng từ số 1', '1311', '5111', 12000, 'BP01' UNION ALL 
SELECT '20120802', '0001', N'Chứng từ số 1', '1311', '33311', 1200, 'BP01' UNION ALL 
SELECT '20120813', '0002', N'Chứng từ số 2', '1311', '5121', 15000, 'BP01' UNION ALL 
SELECT '20120813', '0003', N'Chứng từ số 3', '6411', '1111', 11000, 'BP01' UNION ALL 
SELECT '20120814', '0004', N'Chứng từ số 4', '6421', '1121', 10000, 'BP01' UNION ALL 
SELECT '20120822', '0005', N'Chứng từ số 5', '6418', '1112', 18000, 'BP01' UNION ALL 
SELECT '20120822', '0006', N'Chứng từ số 6', '1311', '5212',  5000, 'BP05' UNION ALL 
SELECT '20120825', '0007', N'Chứng từ số 7', '1111', '5121', 12000, 'BP02' UNION ALL 
SELECT '20120831', '0008', N'Chứng từ số 8', '6211', '1521', 17000, 'BP02' UNION ALL 
SELECT '20120831', '0009', N'Chứng từ số 9', '6211', '1521', 17000, 'BP02' UNION ALL 
SELECT '20120831', '0010', N'Chứng từ số 10','1311', '5211',  9000, 'BP02'  UNION ALL 
SELECT '20120831', '0011', N'Chứng từ số 11','1111', '1311',  9119, 'BP09' 

IF OBJECT_ID('TempDb..#DMPB') IS NOT NULL DROP TABLE #DMPB;
CREATE TABLE #DMPB 
	(
		MA_PB NVARCHAR(25) NOT NULL DEFAULT '',
		TEN_PB NVARCHAR(128) NOT NULL DEFAULT ''
	)

INSERT INTO #DMPB (MA_PB, TEN_PB)
SELECT N'PB01', N'Phòng ban 1' UNION ALL 
SELECT N'PB02', N'Phòng ban 2' UNION ALL 
SELECT N'PB03', N'Phòng ban 3' UNION ALL 
SELECT N'PB04', N'Phòng ban 4' UNION ALL 
SELECT N'PB05', N'Phòng ban 5'

SELECT * FROM #CTTEMP

/*-------------------------------------
Yêu cầu: Viết query có các điều kiện truyền vào :
	+ Từ ngày...Đến ngày...
	+ Tk_DoanhThu dạng danh sách: Ví dụ [511,512,521]
	+ Tk_ChiPhi dạng danh sách: Ví dụ [641,642,622]
- Doanh thu luôn lấy phát sinh Có của các Tk trong list Tk_DoanhThu (Bỏ qua trường hợp giảm trừ)
- Chi phí luôn lấy phát sinh Nợ của các Tk trong list Tk_ChiPhi (Bỏ qua trường hợp giảm trừ)
- Lợi nhuận = Tổng doanh thu - Tổng chi phí
- Doanh thu, Chi phí chi tiết theo từng tài khoản

1/	Lên được báo cáo liệt kê chi tiết Doanh thu, Chi phí, Lợi nhuận của từng bộ phận, dạng như sau:
	----------------|------------
	|   Nội dung	|  Giá trị	|
	----------------|------------
	Phòng ban 1		|	0
	  Doanh thu		|	27000
		+ 511		|	12000
		+ 512		|	15000
	  Chi phí		|	39000
		+ 641		|	29000
		+ 642		|	10000
	  Lợi nhuận		|	-12000
	Phòng ban 2		|	
	  Doanh thu		|	21000
		+ 512		|	12000
		+ 521		|	9000
	  Chi phí		|	8000
		+ 642		|	8000
	  Lợi nhuận		|	13000
	Phòng ban 5		|	
	  Doanh thu		|	5000
		+ 521		|	5000
	  Chi phí		|	0
	  Lợi nhuận		|	5000
	----------------------------

2/	Dựa trên kết quả bài 1, lên được báo cáo dạng cột cho từng bộ phận như sau:
	----------------|------------------------------------
	|   Nội dung		|	PB01	|	PB02	|	PB03	|
	----------------|------------------------------------
	Doanh thu		|	27000	|	21000	|	5000
		+ 511		|	12000	|	0		|	0
		+ 512		|	15000	|	12000	|	0
		+ 521		|	0		|	9000	|	5000
	Chi phí			|	39000	|	8000	|	0
		+ 641		|	29000	|	0		|	0
		+ 642		|	10000	|	8000	|	0
	Lợi nhuận		|	-12000	|	13000	|	5000
