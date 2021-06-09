import { environment } from "../../../environments/environment";

const AUTHORS_API_PUBLIC = environment.bookstore.api.base + environment.bookstore.api.public.base;
const AUTHORS_API_PRIVATE = environment.bookstore.api.base + environment.bookstore.api.private.base;

export const AuthorsApi = {
  public: {
    findAll: AUTHORS_API_PUBLIC + environment.bookstore.api.public.authors.findAll,
    findAllNoLimit: AUTHORS_API_PUBLIC + environment.bookstore.api.public.authors.findAllNoLimit
  },
  private: {
    create: AUTHORS_API_PRIVATE + environment.bookstore.api.private.authors.create
  }
}
