import { environment } from "../../../environments/environment";

const PUBLISHING_HOUSE_API_PUBLIC = environment.bookstore.api.base + environment.bookstore.api.public.base;
const PUBLISHING_HOUSE_API_PRIVATE = environment.bookstore.api.base + environment.bookstore.api.private.base

export const PublishingHouseApi = {
  public: {
    create: PUBLISHING_HOUSE_API_PUBLIC + environment.bookstore.api.public.publishingHouse.create
  },
  private: {
    findAllBooks: PUBLISHING_HOUSE_API_PRIVATE + environment.bookstore.api.private.publishingHouse.findAllBooks
  }
}
