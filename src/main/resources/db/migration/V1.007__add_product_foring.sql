ALTER TABLE public.product
  ADD CONSTRAINT fk_product_ FOREIGN KEY (category_id)
    REFERENCES public.category(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    NOT DEFERRABLE;