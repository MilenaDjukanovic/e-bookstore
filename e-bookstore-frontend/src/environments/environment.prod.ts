export const environment = {
  production: true,
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
          findAll: '/books'
        },
        category: {
          findAll: '/categories',
        },
        publishingHouse: {
          create: '/publishing-houses/create'
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
          findAllBooks: '/publishing-houses/books'
        }
      }
    }
  }
};
