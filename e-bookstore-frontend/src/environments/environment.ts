// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  imgur: {
    clientId: '032019b17719348',
    clientSecret: '',
    uploadUrl: 'https://api.imgur.com/3/image'
  },
  bookstore: {
    api: {
      base: 'spring',
      public: {
        base: '/api/public',
        authors: {
          findAll: '/authors',
          findAllNoLimit: '/authors/all'
        },
        books: {
          findAll: '/books',
          findByTitleAndCategory: '/books/search'
        },
        category: {
          findAll: '/categories',
          findAllNoLimit: '/categories/all'
        },
        publishingHouse: {
          create: '/publishing-houses/create',
          findAllNoLimit: '/publishing-houses/all'
        }
      },
      private: {
        base: '/api/private',
        authors: {
          create: '/authors'
        },
        books: {
          create: '/books'
        },
        bookManagementRequests: {
          findAll: '/book-management-requests',
          findAllPending: '/book-management-requests/pending',
          approve: '/book-management-requests/approve/',
          delete: '/book-management-requests/delete/',
          create: '/book-management-requests/create'
        },
        category: {
          create: '/categories'
        },
        publishingHouse: {
          findAllBooks: '/publishing-houses/books',
          delete: '/publishing-houses/delete/'
        }
      }
    }
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
