import { environment } from "../../../environments/environment";

const BOOKS_API_PUBLIC = environment.bookstore.api.base + environment.bookstore.api.public.base;
const BOOKS_API_PRIVATE = environment.bookstore.api.base + environment.bookstore.api.private.base;

export const BooksApi = {
  public: {
    findAll: BOOKS_API_PUBLIC + environment.bookstore.api.public.books.findAll,
  },
  private: {
    create: BOOKS_API_PRIVATE + environment.bookstore.api.private.books.create
  }
}
