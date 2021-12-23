create table product(
    id UUID not null,
    name varchar not null,
    category_id UUID not null,
    constraint product_pkey primary key (id),
    constraint product_category_fkey foreign key (category_id) references product_category(id)
)