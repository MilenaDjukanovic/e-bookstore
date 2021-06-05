export interface IBasePublishingHouse {
  companyName: string;
  email: string;
  tin: string;
  representativeRegistrationKey: string;
}

export interface ICreatePublishingHouse extends IBasePublishingHouse {
}

export interface IPublishingHouse extends IBasePublishingHouse {
  id: number;
}

export class CreatePublishingHouse implements ICreatePublishingHouse {
  constructor(public companyName: string,
              public email: string,
              public tin: string,
              public representativeRegistrationKey: string) {
  }
}

export class PublishingHouse implements IPublishingHouse {
  constructor(public id: number,
              public companyName: string,
              public email: string,
              public tin: string,
              public representativeRegistrationKey: string) {
  }
}
