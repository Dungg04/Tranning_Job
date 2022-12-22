﻿--1.Tạo CSDL
-- KHACH_HANG (MA_KH, TEN_KH, MST, GIOI_TINH) (MST: dạng ký tự không bắt buộc nhập, GIOI_TINH kiểu INT nhận giá trị 1.Nam, 2.Nữ)
-- HOA_DON (SO_HD, NGAY, MA_KH, DIEN_GIAI, TK_NO, TK_CO, THANH_TIEN) 

CREATE DATABASE KHHD

CREATE TABLE KhachHang (
	MaKH int NOT NULL PRIMARY KEY,
	TenKH nvarchar(100),
	MST nvarchar(50) NULL,
	GioiTinh int 
);

CREATE TABLE HoaDon (
	SoHD int NOT NULL PRIMARY KEY,
	Ngay date,
	MaKH int FOREIGN KEY REFERENCES KhachHang(MaKH),
	DienGiai nvarchar(250),
	TKCo float,
	TKNo float,
	ThanhTien float
);


--Câu 1. Lấy ra danh sách khách hàng
SELECT * FROM KhachHang

--Câu 2. Lấy ra các hóa đơn có mã khách hàng là KH01
SELECT * FROM HoaDon
WHERE MaKH = 1

--Câu 3. Lấy ra các hóa đơn có tổng tiền trên hóa đơn không lớn hơn 50
SELECT * FROM HoaDon
WHERE ThanhTien < 50

SELECT * FROM HoaDon
WHERE NOT ThanhTien >= 50

--Câu 4. Lấy ra các hóa đơn có mã khách hàng là KH01 và số tiền trên hóa đơn không lớn hơn 50000
SELECT * FROM HoaDon
WHERE MaKH = 1 AND ThanhTien < 50000

--Câu 5. Lấy ra các hóa đơn có mã khách hàng là KH01 hoặc số tiền trên hóa đơn không lớn hơn 50000
SELECT * FROM HoaDon
WHERE MaKH = 1 OR ThanhTien < 50000

--Câu 6. Lấy ra các hóa đơn có mã khách hàng là KH01 hoặc KH02 và số tiền trên hóa đơn không lớn hơn 50000
SELECT * FROM HoaDon
WHERE MaKH = 1 AND ThanhTien < 50000
UNION
SELECT * FROM HoaDon
WHERE MaKH = 1 AND ThanhTien < 50000

--Câu 7. Lấy ra các hóa đơn có mã khách hàng là KH01 hoặc KH03 và có năm là 2017
SELECT * FROM HoaDon
WHERE MaKH = 1 AND YEAR(Ngay) = 2017
UNION
SELECT * FROM HoaDon
WHERE MaKH = 3 AND YEAR(Ngay) = 2017

--Câu 8. Lấy ra các khách hàng có mã số thuế null
SELECT * FROM KhachHang
WHERE MST IS NULL

--Câu 9. Lấy ra các hóa đơn có diễn giải chứa kí tự Bán hàng ('Không')
SELECT * FROM HoaDon
WHERE DienGiai = N'Không'

--Câu 10. Lấy ra các hóa đơn có diễn giải bắt đầu với kí tự Bán hàng
SELECT * FROM HoaDon
WHERE DienGiai LIKE 'Bán hàng%'

--Câu 11. Lấy ra các khách hàng có kí tự thứ 2 của mã khách hàng là K
SELECT * FROM KhachHang
WHERE TenKH LIKE '_r%'

--Câu 12. Lấy ra các hóa đơn có mã khách hàng là KH01 hoặc KH02 và có năm là 2022
SELECT * FROM HoaDon
WHERE MaKH = 1 AND YEAR(Ngay) = 2022
UNION
SELECT * FROM HoaDon
WHERE MaKH = 2 AND YEAR(Ngay) = 2022

--Câu 13. Lấy ra các khách hàng có phát sinh hóa đơn trong năm 2022 và có mã số thuế không null
SELECT KhachHang.MaKH, TenKH, MST, GioiTinh FROM KhachHang
JOIN HoaDon ON KhachHang.MaKH = HoaDon.MaKH
WHERE YEAR(Ngay) = 2022 AND MST IS NOT NULL 

--Câu 14. Lấy ra ds khách hàng và các hóa đơn phát sinh
SELECT * FROM KhachHang AS k
JOIN HoaDon AS h ON h.MaKH = h.MaKH

--Câu 15. Lấy ra ds tất cả khách hàng và các hóa đơn phát sinh
--Câu 16. Lấy ra ds tất cả khách hàng và tất cả các hóa đơn phát sinh
--Câu 17. Lấy các hóa đơn có năm 2022, đẩy vào bảng tạm #HD2022
CREATE TABLE #HD2022 (
	SoHD int,
	Ngay date,
	MaKH int,
	DienGiai nvarchar(250),
	TKCo float,
	TKNo float,
	ThanhTien float
);


INSERT INTO #HD2022
SELECT * FROM HoaDon
WHERE YEAR(Ngay)=2022

SELECT * FROM #HD2022

--Câu 18. Lấy tất cả các khách hàng, nếu trường MST là Null thì gán giá trị 123
SELECT MaKH, TenKH, ISNULL(MST, 123) AS MST, GioiTinh FROM KhachHang

--Câu 19. Lấy ra danh sách khách hàng và giới tính (1.Nam, 2 Nữ)
SELECT MaKH, TenKH, MST,
CASE
	WHEN GioiTinh = 1 THEN 'Nam'
	WHEN GioiTinh = 2 THEN N'Nữ'
END AS GioiTinh
FROM KhachHang

/*Câu 20. Viết ra báo cáo dạng sau :
	|Số HD           Diễn giải			             Ngày	        Thành tiền        
	|---------------- ----------------------        ----- -       ------------- 
	|				  KH01 - Khách hàng KH01                            7000
	|HD01			  Số hóa đơn : HD01 (15/01/2017)  15/01/2017        1000 
	|HD02			  Số hóa đơn : HD02 (15/01/2017)  15/01/2017        2000      
	|HD03			  Số hóa đơn : HD03 (17/01/2017)  17/01/2017        4000    
	|				  KH02 - Khách hàng KH02                            8000
	|HD05			  Số hóa đơn : HD05 (15/01/2017)  15/01/2017        1000 
	|HD06			  Số hóa đơn : HD06 (16/01/2017)  16/01/2017        3000      
	|HD07			  Số hóa đơn : HD07 (17/01/2017)  17/01/2017        4000    
	| 				  Tổng cộng			                                15000            
	|---------------- ---------------- -------------- -------------- -------------- ------------
*/

SELECT SoHD AS N'Số HD', N'Số hoá đơn : ' + CONVERT(nvarchar, SoHD) + ' (' + CONVERT(nvarchar, Ngay) + ') '  AS N'Diễn giải',
	Ngay AS N'Ngày', ThanhTien AS N'Thành tiền'
FROM HoaDon




--Cho cơ sở dũ liệu sau
SET NOCOUNT ON;
SET DATEFORMAT DMY;

-- Danh mục vật tư
IF OBJECT_ID('TempDb..#DMVTHH') IS NOT NULL DROP TABLE #DMVTHH;
CREATE TABLE #DMVTHH 
(	MA_VT VARCHAR(16), 
	TEN_VT VARCHAR(88)
);
INSERT INTO #DMVTHH (MA_VT, TEN_VT) VALUES ('VTHH01', 'Vat tu hang hoa 01')
INSERT INTO #DMVTHH (MA_VT, TEN_VT) VALUES ('VTHH02', 'Vat tu hang hoa 02')
INSERT INTO #DMVTHH (MA_VT, TEN_VT) VALUES ('VTHH03', 'Vat tu hang hoa 03')
INSERT INTO #DMVTHH (MA_VT, TEN_VT) VALUES ('VTHH04', 'Vat tu hang hoa 04')
INSERT INTO #DMVTHH (MA_VT, TEN_VT) VALUES ('VTHH05', 'Vat tu hang hoa 05')

-- Tồn đầu
IF OBJECT_ID('TempDb..#TON_DAU_KY') IS NOT NULL DROP TABLE #TON_DAU_KY;
CREATE TABLE #TON_DAU_KY 
(	MA_KHO VARCHAR(16), 
	MA_VT VARCHAR(16), 
	TON_DAU_KY DECIMAL(18, 0)
);

INSERT INTO #TON_DAU_KY (Ma_Kho, Ma_Vt, TON_DAU_KY) VALUES ('KHO1', 'VTHH01', 15)
INSERT INTO #TON_DAU_KY (Ma_Kho, Ma_Vt, TON_DAU_KY) VALUES ('KHO1', 'VTHH02', 20)
INSERT INTO #TON_DAU_KY (Ma_Kho, Ma_Vt, TON_DAU_KY) VALUES ('KHO1', 'VTHH05', 8)

-- Nhập xuất trong kỳ
IF OBJECT_ID('TempDb..#NHAP_XUAT_KHO') IS NOT NULL DROP TABLE #NHAP_XUAT_KHO;
CREATE TABLE #NHAP_XUAT_KHO 
(	NGAY_CHUNG_TU Smalldatetime,
	MA_KHO VARCHAR(16), 
	MA_VT VARCHAR(16), 
	NHAP DECIMAL(18, 0),
	XUAT DECIMAL(18, 0)
);

INSERT INTO #NHAP_XUAT_KHO (NGAY_CHUNG_TU, MA_KHO, MA_VT, NHAP, XUAT) VALUES ('01/01/11', 'KHO1', 'VTHH01', 4, 0)
INSERT INTO #NHAP_XUAT_KHO (NGAY_CHUNG_TU, MA_KHO, MA_VT, NHAP, XUAT) VALUES ('01/01/11', 'KHO1', 'VTHH03', 10, 0)
INSERT INTO #NHAP_XUAT_KHO (NGAY_CHUNG_TU, MA_KHO, MA_VT, NHAP, XUAT) VALUES ('02/01/11', 'KHO1', 'VTHH05', 0, 5)
INSERT INTO #NHAP_XUAT_KHO (NGAY_CHUNG_TU, MA_KHO, MA_VT, NHAP, XUAT) VALUES ('04/01/11', 'KHO1', 'VTHH02', 0, 12)
INSERT INTO #NHAP_XUAT_KHO (NGAY_CHUNG_TU, MA_KHO, MA_VT, NHAP, XUAT) VALUES ('05/01/11', 'KHO2', 'VTHH04', 14, 0)
INSERT INTO #NHAP_XUAT_KHO (NGAY_CHUNG_TU, MA_KHO, MA_VT, NHAP, XUAT) VALUES ('05/01/11', 'KHO2', 'VTHH04', 0, 10)

--SELECT * FROM #TON_DAU_KY
--SELECT * FROM #NHAP_XUAT_KHO

/* Yêu cầu
1/ Viết ra báo cáo dạng Nhập xuất tồn:
	- Điều kiện là Ma_Kho. Truyền vào kho nào thì lên kho đó, không truyền kho thì lên hết	

	|MA_KHO           MA_VT				TON_DAU_KY        NHAP           XUAT           TON_CUOI
	|---------------- ----------------	-------------- -------------- -------------- ------------
	|K1               VTHH01 - Vat tu hang hoa 01  15              4               0              19
	|K1               VTHH02 - Vat tu hang hoa 02  20              0              12               8
	|K1               VTHH03 - Vat tu hang hoa 03   0             10               0              10
	|K1               VTHH05 - Vat tu hang hoa 04   8              0               5               3
	|K2               VTHH04 - Vat tu hang hoa 05   0             14              10               4
	| 				  Tong cong			43             28              27              44
	|---------------- ---------------- -------------- -------------- -------------- ------------
*/


