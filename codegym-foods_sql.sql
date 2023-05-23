use codegym_foods;
insert into customer_type(`name`)
value ("Khách hàng thân thiết"),
("Khách hàng Vàng"),
("Khách hàng Bạch Kim"),
("Khách hàng Kim Cương");

insert into customer(address, avatar, date_of_birth, email, `name`, phone_number)
value("Đà Nẵng", null, "02-05-2000", "123@gmail.com", "huy", "0943924834"),
("Huế", null, "17-02-2001", "345@gmail.com", "huy", "0934827592"),
("Hà Nội", null, "20-01-2002", "678@gmail.com", "huy", "0329462944"),
("Sài Gòn", null, "11-08-2003", "987@gmail.com", "huy", "0329584028"),
("Đà Nẵng", null, "07-07-2004", "235@gmail.com", "huy", "0943478291");