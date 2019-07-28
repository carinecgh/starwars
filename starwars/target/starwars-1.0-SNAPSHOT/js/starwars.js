/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module("StarWarsApp", [])
        .value('urlBase', 'http://localhost:8084/starwars/rest/planetas/')
        .controller("PlanetaController", function ($http, urlBase) {
            var self = this;

            self.planetas = [];
            self.planetasPesquisados = [];
            self.planeta = undefined;
            self.planetaPesquisa = undefined;

            self.novo = function () {
                self.planeta = {};
            };
            
            self.pesquisa = function () {
                self.planetaPesquisa = {};
            };

            self.salvar = function () {
                $http({
                    method: 'POST',
                    url: urlBase,
                    data: self.planeta
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.deletar = function (planeta) {
                self.planeta = planeta;

                $http({
                    method: 'DELETE',
                    url: urlBase + self.planeta.id + '/'
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };
            
            self.pesquisar = function () {
                $http({
                    method: 'GET',
                    url: urlBase + 'pesquisa/'+self.planetaPesquisa.id+'/'+self.planetaPesquisa.nome
                }).then(function successCallback(response) {
                    self.planetasPesquisados = response.data;
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });            
            };

            self.ocorreuErro = function () {
                alert("Ocorreu um erro!\n Verifique se você preencheu corretamente os campos");
            };

            self.atualizarTabela = function () {
                $http({
                    method: 'GET',
                    url: urlBase
                }).then(function successCallback(response) {
                    self.planetas = response.data;
                    self.planetasPesquisados = undefined;
                    self.planeta = undefined;
                    self.planetaPesquisa = undefined;
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };
            
            self.activate = function () {
                self.atualizarTabela();
            };
            self.activate();
        });