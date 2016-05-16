angular.module(angularApp + '.controllers')
    .factory('httpInterceptor', ['$rootElement', '$q', function ($rootElement, $q) {
        return {
            response: function (response) {
                if (response.headers()['internal-server-exception']) {
                    angular.element($rootElement).replaceWith(response.data);
                    return $q.reject(response);
                }
                return response;
            }
        };
    }])
    .factory('tableService', function ($http) {
        return {
            getColumn: function () {
                return $http({
                    method: 'GET',
                    url: 'columns.json'
                });
            }
        }
    })
    .factory('Data', function () {
        return {
            currentPage: 0,
            numOfDemographics: 10,
            numOfPages: 0,
            maxSize: 10,
            totalItems: 0,
            demographics: {},
            viewDemographics: {}
        };
    });
