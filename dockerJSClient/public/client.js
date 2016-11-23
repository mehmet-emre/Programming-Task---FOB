var app = angular.module('demo', []);
var uri = 'http://192.168.99.100:8080';
app.controller('Main', function($scope, $http) {
    $scope.viewStack = function () {
        $scope.messageText = "";
        $http.get(uri + '/stackservice/view', {
            headers: {authorization: '0f65448e9f4f195c073d36faf576c69e??=='}}).
            then(function(response) {
                $scope.messageText = response.data;
            });
    }
    $scope.pushItem = function () {
        $scope.messageText = "";
        if (!$scope.pushMe) {return;}
        $http.get(uri + '/stackservice/push?item='+$scope.pushMe, {
            headers: {authorization: '0f65448e9f4f195c073d36faf576c69e??=='}}).
            then(function(response) {
                $scope.messageText = response.data;
            });
    }
    $scope.popItem = function () {
        $scope.messageText = "";
        $http.get(uri + '/stackservice/pop', {
            headers: {authorization: '0f65448e9f4f195c073d36faf576c69e??=='}}).
            then(function(response) {
                $scope.messageText = response.data;
            });
    }
});
