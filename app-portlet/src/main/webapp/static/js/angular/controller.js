//'use strict';
angular.module(angularApp + '.controllers')
    .controller('demographicController', ['$scope', '$location',
        'demographicService', 'Data', function ($scope, $location, demographicService, Data) {

            $scope.data = Data;

            //Initialize table to default
            initTable();
            //get all demographics
            function initTable() {
                $scope.demographic = null;
                if ($scope.data.numOfDemographics > 0) {
                    demographicService.getDemographics($scope.data.currentPage, $scope.data.numOfDemographics, null).success(function (response) {
                        $scope.data.demographics = response.content;
                        console.log(response);
                        $scope.currentPage = response.number;
                        $scope.data.viewDemographics = $scope.data.demographics;
                        $scope.data.numOfPages = response.totalPages - 1;
                        $scope.data.totalItems = response.totalElements;
                    });
                }
            }

            //Search for data
            function searchData() {
                $scope.demographic = null;
                $scope.demographicImage = null;
                $scope.demographicPrints = null;
                console.log($scope.query);
                demographicService.getDemographics($scope.data.currentPage, $scope.data.numOfDemographics, $scope.query).success(function (response) {
                    $scope.data.demographics = response.content;
                    console.log(response);
                    $scope.currentPage = response.number;
                    $scope.data.viewDemographics = $scope.data.demographics;
                    $scope.data.numOfPages = (response.totalPages) - 1;
                    $scope.data.totalItems = response.totalElements;
                });
            }

            $scope.search = function () {
                //if($scope.query !=null) {
                //    $scope.currentPageNum = 0;
                //    $scope.viewDemographics = null;
                //    $scope.numOfPages = 0;
                //    $scope.totalItems = 0;
                searchData();
                //}
            };

            $scope.showDetails = function (id) {
                demographicService.findById(id).success(function (response) {
                    $scope.demographic = response;
                    $scope.fetchImage(response.id);
                    $scope.fetchFingerPrints(response.id);
                    $scope.data.totalItems = 0;
                    console.log($scope.demographic);
                    $scope.PageNo = $scope.data.currentPage;
                    $location.path("/view");
                });
            }

            $scope.fetchImage = function (id) {
                demographicService.getDemographicImage(id).success(function (image) {
                    $scope.demographicImage = image;
                });
                //
                return $scope.demographicImage;
            }

            $scope.fetchFingerPrints = function (id) {
                demographicService.getDemographicPrints(id).success(function (prints) {
                    $scope.demographicPrints = prints;
                });
            }


            $scope.updateView = function () {
                //$scope.currentPage = currentPage;
                console.log($scope.data.currentPage);

                if ($scope.query == null) {
                    initTable();
                    console.log("inside table init: " + $scope.data.currentPage);
                } else {
                    searchData();
                    console.log("inside search:" + $scope.data.currentPage);

                }

            }

            $scope.setPage = function (pageNo) {
                $scope.data.currentPage = pageNo;
                $scope.demographic = null;

                $scope.updateView();

            };

        }]);


app.controller('DocCtrl', function ($scope) {

    $scope.pdfName = 'Relativity: The Special and General Theory by Albert Einstein';
    $scope.pdfUrl = 'pdf/relativity.pdf';
    $scope.scroll = 0;
    $scope.loading = 'loading';

    $scope.getNavStyle = function (scroll) {
        if (scroll > 100) return 'pdf-controls fixed';
        else return 'pdf-controls';
    }

    $scope.onError = function (error) {
        console.log(error);
    }

    $scope.onLoad = function () {
        $scope.loading = '';
    }

    $scope.onProgress = function (progress) {
        console.log(progress);
    }

});