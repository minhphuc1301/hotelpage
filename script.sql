
/****** Object:  Database [MoonCakeStore]    Script Date: 23/09/2021 08:39:12 ******/
CREATE DATABASE [MoonCakeStoreeeasd]

GO
ALTER DATABASE [MoonCakeStore] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MoonCakeStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MoonCakeStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MoonCakeStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MoonCakeStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MoonCakeStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MoonCakeStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [MoonCakeStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MoonCakeStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MoonCakeStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MoonCakeStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MoonCakeStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MoonCakeStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MoonCakeStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MoonCakeStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MoonCakeStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MoonCakeStore] SET  DISABLE_BROKER 
GO
ALTER DATABASE [MoonCakeStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MoonCakeStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MoonCakeStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MoonCakeStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MoonCakeStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MoonCakeStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MoonCakeStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MoonCakeStore] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [MoonCakeStore] SET  MULTI_USER 
GO
ALTER DATABASE [MoonCakeStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MoonCakeStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MoonCakeStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MoonCakeStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [MoonCakeStore] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [MoonCakeStore] SET QUERY_STORE = OFF
GO
USE [MoonCakeStore]
GO
/****** Object:  Table [dbo].[tblCategories]    Script Date: 23/09/2021 08:39:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategories](
	[CategoryID] [int] NOT NULL,
	[CategoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblCategories_1] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 23/09/2021 08:39:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[orderID] [nvarchar](50) NOT NULL,
	[productID] [nvarchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
	[unitPrice] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 23/09/2021 08:39:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [nvarchar](50) NOT NULL,
	[userID] [nvarchar](50) NULL,
	[total] [float] NULL,
	[status] [nvarchar](50) NULL,
	[orderDate] [date] NULL,
	[shipAddress] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblOrders] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProduct]    Script Date: 23/09/2021 08:39:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProduct](
	[productID] [nvarchar](50) NOT NULL,
	[productName] [nvarchar](500) NULL,
	[description] [nvarchar](1000) NULL,
	[price] [float] NULL,
	[unitInstock] [int] NULL,
	[CategoryID] [int] NULL,
	[imageUrl] [nvarchar](500) NULL,
	[createDate] [date] NULL,
	[expirationDate] [date] NULL,
	[status] [nvarchar](50) NULL,
	[lastUpdate] [date] NULL,
	[updateBy] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblProduct] PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 23/09/2021 08:39:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NULL,
	[fullName] [nvarchar](50) NULL,
	[roleID] [nvarchar](50) NULL,
	[gender] [nvarchar](6) NULL,
	[phone] [nvarchar](12) NULL,
	[address] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName]) VALUES (1, N'Golden Baked Mooncake')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName]) VALUES (2, N'Soft Sticky Rice Mooncake')
GO
INSERT [dbo].[tblOrderDetails] ([orderID], [productID], [quantity], [unitPrice]) VALUES (N'OR0', N'CAKE007', 1, 85)
INSERT [dbo].[tblOrderDetails] ([orderID], [productID], [quantity], [unitPrice]) VALUES (N'OR0', N'CAKE001', 1, 5)
INSERT [dbo].[tblOrderDetails] ([orderID], [productID], [quantity], [unitPrice]) VALUES (N'OR0', N'CAKE000', 1, 40)
INSERT [dbo].[tblOrderDetails] ([orderID], [productID], [quantity], [unitPrice]) VALUES (N'OR0', N'CAKE003', 1, 50)
GO
INSERT [dbo].[tblOrders] ([orderID], [userID], [total], [status], [orderDate], [shipAddress]) VALUES (N'OR0', N'phuc1', 85, N'In Progress', CAST(N'2021-09-23' AS Date), N'136  Lau 2 ')
GO
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE000', N'Soft sticky mooncake mixed 180G', N'A delicous cake', 40, 4, 2, N'20180810183033_Main-product-detail.png', CAST(N'2021-01-13' AS Date), CAST(N'2021-02-13' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE001', N'Tiramisu Soft Sticky Mooncake', N'A good taste', 5, 4, 2, N'banh-trung-thu-tiramisu.jpg', CAST(N'2021-01-14' AS Date), CAST(N'2023-03-15' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE002', N'Nut Sticky Soft Mooncake', N'A nice cake', 30, 5, 2, N'Nhan-Banh_Banh-deo-600x600.jpg', CAST(N'2020-02-02' AS Date), CAST(N'2021-01-13' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE003', N'Nuttter Sticky Soft Mooncake', N'A delicous cake', 50, 4, 2, N'banh-trung-thu-lava.jpg', CAST(N'2020-12-13' AS Date), CAST(N'2021-01-13' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE004', N'Lava Soft Sticky', N'A delicous cake', 15, 1, 2, N'banh-trung-thu-yen-sao-dau-xanh.jpg', CAST(N'2020-12-13' AS Date), CAST(N'2021-01-13' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE005', N'Than Tre Golden Baked Mooncake', N'A good cake', 90, 5, 1, N'banh-trung-thu-than-tre-so-diep.jpg', CAST(N'2020-01-13' AS Date), CAST(N'2021-02-13' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE006', N'Bao Ngu Golden Baked Mooncake', N'A priceless cake', 100, 5, 1, N'banh-trung-thu-bao-ngu.jpg', CAST(N'2020-01-13' AS Date), CAST(N'2022-02-13' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE007', N'Huynh De Golden Baked Mooncake', N'A seafood mooncake', 85, 4, 1, N'banh-trung-thu-cua-huynh-de.jpg', CAST(N'2021-02-25' AS Date), CAST(N'2022-03-20' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE008', N'Jambon Golden Baked Mooncake', N'A jambon mooncake', 50, 5, 1, N'banh-trung-thu-jambon.jpg', CAST(N'2020-02-25' AS Date), CAST(N'2022-03-20' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
INSERT [dbo].[tblProduct] ([productID], [productName], [description], [price], [unitInstock], [CategoryID], [imageUrl], [createDate], [expirationDate], [status], [lastUpdate], [updateBy]) VALUES (N'CAKE009', N'Green Tea Soft Sticky Rice Mooncake', N'A greentea mooncake', 65, 5, 2, N'banh-trung-thu-tra-xanh-600x600.jpg', CAST(N'2019-02-25' AS Date), CAST(N'2022-03-20' AS Date), N'Active', CAST(N'2021-09-23' AS Date), N'admin')
GO
INSERT [dbo].[tblUser] ([username], [password], [fullName], [roleID], [gender], [phone], [address]) VALUES (N'admin', N'1', N'AD', N'AD', N'Male', N'0909710750', N'125 Lau 2')
INSERT [dbo].[tblUser] ([username], [password], [fullName], [roleID], [gender], [phone], [address]) VALUES (N'phuc', N'1', N'Nguyen Minh Phuc', N'US', N'Male', N'0909710750', N'125 Lau 2')
INSERT [dbo].[tblUser] ([username], [password], [fullName], [roleID], [gender], [phone], [address]) VALUES (N'phuc1', N'1', N'Nguyen Minh Phuoc', N'US', N'Male', N'0903240343', N'136  Lau 2')
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblOrders] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblOrders]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblProduct] FOREIGN KEY([productID])
REFERENCES [dbo].[tblProduct] ([productID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblProduct]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK_tblOrders_tblUser] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([username])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK_tblOrders_tblUser]
GO
ALTER TABLE [dbo].[tblProduct]  WITH CHECK ADD  CONSTRAINT [FK_tblProduct_tblCategories] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[tblCategories] ([CategoryID])
GO
ALTER TABLE [dbo].[tblProduct] CHECK CONSTRAINT [FK_tblProduct_tblCategories]
GO
ALTER TABLE [dbo].[tblProduct]  WITH CHECK ADD  CONSTRAINT [FK_tblProduct_tblUser] FOREIGN KEY([updateBy])
REFERENCES [dbo].[tblUser] ([username])
GO
ALTER TABLE [dbo].[tblProduct] CHECK CONSTRAINT [FK_tblProduct_tblUser]
GO
USE [master]
GO
ALTER DATABASE [MoonCakeStore] SET  READ_WRITE 
GO
