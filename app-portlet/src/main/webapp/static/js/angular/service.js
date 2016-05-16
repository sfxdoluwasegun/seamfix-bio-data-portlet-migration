angular.module(angularApp + '.services')
    .service('demographicService', ['$http', function ($http) {
        this.getDemographics = function (page, size, search) {
            return $http({
                method: 'GET',
                url: resourceURL,
                params: {
                    page: page,
                    size: size,
                    search: search
                }
            });
        }
        this.searchDemographics = function (page, size, search) {
            return $http({
                method: 'GET',
                url: searchURL,
                params: {
                    page: page,
                    size: size,
                    search: search
                }
            });
        }
        this.findById = function (id) {
            return $http({
                method: 'GET',
                url: detailURL,
                params: {
                    id: id
                }
            });
        }
        this.getDemographicImage = function (id) {
            return $http({
                method: 'GET',
                url: imageURL,
                params: {
                    id: id
                }
            });
        }
        this.getDemographicPrints = function (id) {
            return $http({
                method: 'GET',
                url: printsURL,
                params: {
                    id: id
                }
            });
        }
    }]);
//.service('demographicService', ['$http', function ($http) {

//}]);


