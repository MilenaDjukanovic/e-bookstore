import {PublishingHouse} from "./publishing-house.model";
import {IBook} from "./book.model";

export interface IBaseBookManagementRequests {
  quantity: number;
  processedByUserId: number;
  reason: string;
  processed: boolean;
}

export interface ICreateBookManagementRequest extends IBaseBookManagementRequests {
  bookId: number;
  publishingHouseId: number;
}

export interface IBookManagementRequest extends IBaseBookManagementRequests {
  Book: IBook;
  publishingHouse: PublishingHouse;
}

export class CreateBookManagementRequest implements ICreateBookManagementRequest {
  constructor(public bookId: number,
              public quantity: number,
              public processedByUserId: number,
              public reason: string,
              public processed: boolean,
              public publishingHouseId: number) {
  }
}

export class BookManagementRequest implements IBookManagementRequest{
  constructor(public Book: IBook,
              public quantity: number,
              public processedByUserId: number,
              public reason: string,
              public processed: boolean,
              public publishingHouse: PublishingHouse) {
  }
}
