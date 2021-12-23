create table product_category(
    id UUID not null,
    name varchar not null,
    constraint product_category_pkey primary key (id)
);
insert into
 product_category(id, name)
values
 ('f5dbc5e7-4ab6-40f6-93ad-209116fa49a1','Café da manhã/Padaria'),
 ('f24b833d-672c-45d4-9690-7a7b2d87f281','Mercearia em geral e enlatados'),
 ('343ef472-8e6a-493a-bfb1-252bc1802ee7','Bebidas'),
 ('7a4b5ca8-4beb-41ec-941f-2e44abafb05d','temperos'),
 ('69882a7f-21ae-4872-8818-49d45bd8d81a','Frutas e Legumes'),
 ('352db31d-8d11-4f6c-b4ee-b39b7cf63be4','Carnes e frios'),
 ('df5c8c85-00a3-42f2-95c6-210bc5b0948a','Pet'),
 ('2968f7fa-1b44-4794-b830-3449d1d16ebc','Produtos de Limpeza/Utilidades'),
 ('e12f64ba-e507-4dd1-ad33-07060d93285b','Higiene pessoal');
