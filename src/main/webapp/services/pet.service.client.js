(function () {
    angular
        .module("Pokepedia")
        .factory('PetService', petService);

    function petService($http) {
        return {
            "createPetForPerson": createPetForPerson,
            "updatePetForPerson": updatePetForPerson,
            "updatePet": updatePet,
            "createPet": createPet,
            "findAllPets": findAllPets,
            "findPetById": findPetById,
            "deletePet": deletePet
        };

        function createPetForPerson(pet, personId) {
            return $http.post("/api/person/" + personId + "/pet", pet);
        }

        function updatePetForPerson(pet, personId) {
            return $http.put("/api/person/" + personId + "/pet/" + pet.id, pet);
        }

        function createPet(pet) {
            return $http.post("/api/pet", pet);
        }

        function updatePet(pet) {
            return $http.put("/api/pet/" + pet.id, pet);
        }

        function findAllPets() {
            return $http.get("/api/pet");
        }

        function deletePet(petId) {
            return $http.delete("/api/pet/" + petId);
        }

        function findPetById(petId) {
            return $http.get("/api/pet/" + petId);
        }

    }
})();
