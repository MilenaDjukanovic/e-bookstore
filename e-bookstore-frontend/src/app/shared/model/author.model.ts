export interface IBaseAuthor{
  firstName: string;
  lastName: string;
  birthYear: number;
  about: string;
}

export interface ICreateAuthor extends IBaseAuthor {

}

export interface IAuthor extends IBaseAuthor {
  id: number;
}

export class CreateAuthor implements ICreateAuthor {
  constructor(public firstName: string,
              public lastName: string,
              public birthYear: number,
              public about: string) {
  }
}

export class Author implements IBaseAuthor {
  constructor(public id: number,
              public firstName: string,
              public lastName: string,
              public birthYear: number,
              public about: string) {
  }
}
