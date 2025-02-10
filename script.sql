INSERT INTO categories(id, name, description, active)
VALUES (1, 'Men', '', 1),
       (2, 'Women', '', 1)


INSERT INTO products(id, name, description, image_url, price)
VALUES (1, 'Running shoes for men',
        'Buy good shoes and a good mattress, because when you''re not in one you''re in the other. With four pairs of shoes, I can travel the world.',
        '', 20)


INSERT INTO product_category(product_id, category_id)
VALUES (1, 1)
GO