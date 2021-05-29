import {Author} from "./author.model";
import {Category} from "./category.model";
import {PublishingHouse} from "./publishing-house.model";

export interface IBaseBook{
    title: string;
    description: string;
    imageUrl: string;
    price: number;
    inStock: number;
    author: Author;
    category: Category;
    publishingHouse: PublishingHouse;
}

export interface ICreateBook extends IBaseBook {

}

export interface  IBook extends IBaseBook {
    id: number;
    sold: number;
}

export class CreateBook implements ICreateBook {
  constructor(public title: string,
              public description: string,
              public imageUrl: string,
              public price: number,
              public inStock: number,
              public author: Author,
              public category: Category,
              public publishingHouse: PublishingHouse) {
  }
}

export class Book implements IBook {
  constructor(public id: number,
              public title: string,
              public description: string,
              public imageUrl: string,
              public price: number,
              public inStock: number,
              public sold: number,
              public author: Author,
              public category: Category,
              public publishingHouse: PublishingHouse) {
  }
}
