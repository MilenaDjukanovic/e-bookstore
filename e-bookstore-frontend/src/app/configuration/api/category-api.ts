import { environment } from "../../../environments/environment";

const CATEGORY_API_PUBLIC = environment.bookstore.api.base + environment.bookstore.api.public.base;
const CATEGORY_API_PRIVATE = environment.bookstore.api.base + environment.bookstore.api.private.base

export const CategoryApi = {
  public: {
    findAll: CATEGORY_API_PUBLIC + environment.bookstore.api.public.category.findAll
  },
  private: {
    create: CATEGORY_API_PRIVATE + environment.bookstore.api.private.category.create
  }
}
