-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS Shop;
USE Shop;
DROP TABLE IF EXISTS T_Order_Items ;
DROP TABLE IF EXISTS T_Orders;
DROP TABLE IF EXISTS T_Customers;
DROP TABLE IF EXISTS T_Users;
DROP TABLE IF EXISTS T_Categories;
DROP TABLE IF EXISTS T_Articles;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des articles en vente                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Articles (
	IdArticle			int		PRIMARY KEY AUTO_INCREMENT,
	Description			varchar(30)	NOT NULL,
	Brand				varchar(30)	NOT NULL,
	UnitaryPrice		float(8)	NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES 
    ( 'Souris', 'Logitoch', 65 ),
    ( 'Clavier', 'Microhard', 49.5 ),
    ( 'Systeme d''exploitation', 'Fenetres Vistouille', 150 ),
    ( 'Tapis souris', 'Chapeau Bleu', 5 ),
    ( 'Cle USB 8 To', 'Syno', 8 ),
    ( 'Laptop', 'PH', 1199 ),
    ( 'CD x 500', 'CETME', 250 ),
    ( 'DVD-R x 100', 'CETME', 99 ),
    ( 'DVD+R x 100', 'CETME', 105 ),
    ( 'Batterie Laptop', 'PH', 80 ),
    ( 'Casque Audio', 'Syno', 105 ),
    ( 'WebCam', 'Logitoch', DEFAULT );

SELECT * FROM T_Articles;

CREATE TABLE T_Categories (
	IdCategory INT PRIMARY KEY AUTO_INCREMENT,
	CatName VARCHAR(30) NOT NULL,
	Description VARCHAR(100) NOT NULL
 ) ENGINE = InnoDB;

-- ALTER TABLE t_articles ADD COLUMN IdCategory INT;
-- ALTER TABLE T_Articles ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);

-- select IdArticle,T_Articles.Description,Brand,UnitaryPrice,T_Articles.IdCategory,CatName,T_Categories.Description 
-- from t_articles inner join t_categories where t_articles.IdCategory = t_categories.IdCategory and IdArticle=1;

-- SELECT IdArticle,t_articles.Description,brand,UnitaryPrice,CatName FROM t_articles 
-- INNER JOIN t_categories WHERE t_articles.IdCategory=t_categories.IdCategory AND IdArticle>10 ORDER BY UnitaryPrice;

CREATE TABLE T_Users (
	IdUser				int		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL UNIQUE,
	Password			varchar(20)	NOT NULL
) ENGINE = InnoDB;

CREATE TABLE T_Customers (
	IdCustomer			int			PRIMARY KEY AUTO_INCREMENT,
	name				varchar(20) 	NOT NULL,
	firstName			varchar(20) 	NOT NULL,
	email				varchar(45) 	NOT NULL,	
	phone				varchar(45) 	NOT NULL,
	address				varchar(90) 	NOT NULL,
	IdUser           	INT   NOT NULL,
	CONSTRAINT fk_T_users_T_customers_IdUser FOREIGN KEY(IdUser) REFERENCES T_Users(IdUser) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE T_Orders (
	IdOrder			int	PRIMARY KEY AUTO_INCREMENT,
	Amount			float(4)	NOT NULL DEFAULT 0,
	DateOrder 		DATE		NOT NULL DEFAULT NOW(),
	IdCustomer      INT   	NOT NULL,
	CONSTRAINT fk_T_Customer_T_Order_IdCustomer FOREIGN KEY(IdCustomer) REFERENCES T_Customers(IdCustomer) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE T_Order_Items (
	IdOrderItem			int	PRIMARY KEY AUTO_INCREMENT,
	
	IdArticle         INT   NOT NULL,
	CONSTRAINT fk_T_Articles_T_Order_Item_IdArticle FOREIGN KEY(IdArticle) REFERENCES T_Articles(IdArticle) ON DELETE CASCADE,
	
	Quantity				INT NOT NULL DEFAULT 1,
	UnitaryPrice		FLOAT(4)	NOT NULL DEFAULT 0,
	
	IdOrder           INT   NOT NULL,
	CONSTRAINT fk_T_Orders_T_Order_Item_IdOrder FOREIGN KEY(IdOrder) REFERENCES T_Orders(IdOrder) ON DELETE CASCADE
) ENGINE = InnoDB;