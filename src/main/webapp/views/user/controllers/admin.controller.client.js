(function () {
    angular
        .module('Pokepedia')
        .controller('AdminController', adminController);

    function adminController(TrainerService, GymLeaderService, PetService, UserService) {
        let vm = this;

        vm.trainer = {};
        vm.trainers = [];

        vm.gymLeader = {};
        vm.gymLeaders = [];

        vm.pet = {};
        vm.pets = [];

        vm.persons = [];

        vm.createTrainer = createTrainer;
        vm.deleteTrainer = deleteTrainer;
        vm.updateTrainer = updateTrainer;
        vm.selectTrainer = selectTrainer;

        vm.createGymLeader = createGymLeader;
        vm.deleteGymLeader = deleteGymLeader;
        vm.updateGymLeader = updateGymLeader;
        vm.selectGymLeader = selectGymLeader;

        vm.createPet = createPet;
        vm.deletePet = deletePet;
        vm.updatePet = updatePet;
        vm.selectPet = selectPet;

        function init() {
            findAllTrainers();
            findAllGymLeaders();
            findAllPets();
            findAllPersons()
        }

        init();

        // trainers

        function findAllTrainers() {
            TrainerService
                .findAllTrainers()
                .then(renderTrainers, errorHandler);
        }

        function createTrainer(trainer) {
            TrainerService
                .createTrainer(trainer)
                .then(findAll, errorHandler);
        }

        function updateTrainer(trainer) {
            TrainerService
                .updateTrainer(trainer)
                .then(findAll, errorHandler);
        }

        function deleteTrainer(trainerID) {
            TrainerService
                .deleteTrainer(trainerID)
                .then(findAll, errorHandler);
        }

        function selectTrainer(trainer) {
            TrainerService.findTrainerById(trainer.id)
                .then(response => {
                    vm.trainer = response.data;
                }, errorHandler);
        }

        function renderTrainers(response) {
            vm.trainers = response.data;
            vm.trainer = {};
        }

        // gym leaders

        function findAllGymLeaders() {
            GymLeaderService
                .findAllGymLeaders()
                .then(renderGymLeaders, errorHandler);
        }

        function createGymLeader(gymLeader) {
            GymLeaderService
                .createGymLeader(gymLeader)
                .then(findAll, errorHandler);
        }

        function updateGymLeader(gymLeader) {
            GymLeaderService
                .updateGymLeader(gymLeader)
                .then(findAll, errorHandler);
        }

        function deleteGymLeader(gymLeaderId) {
            GymLeaderService
                .deleteGymLeader(gymLeaderId)
                .then(findAll, errorHandler);
        }

        function selectGymLeader(gymLeader) {
            GymLeaderService.findGymLeaderById(gymLeader.id)
                .then(response => {
                    vm.gymLeader = response.data;
                }, errorHandler);
        }

        function renderGymLeaders(response) {
            vm.gymLeaders = response.data;
            vm.gymLeader = {};
        }

        // pets

        function findAllPets() {
            PetService
                .findAllPets()
                .then(renderPets, errorHandler);
        }

        function createPet(pet) {
            PetService
                .createPetForPerson(pet, pet.person.id)
                .then(findAll, errorHandler);
        }

        function updatePet(pet) {
            PetService
                .updatePetForPerson(pet, pet.person.id)
                .then(findAll, errorHandler);
        }

        function deletePet(petId) {
            PetService
                .deletePet(petId)
                .then(findAll, errorHandler);
        }

        function selectPet(pet) {
            PetService.findPetById(pet.id)
                .then(response => {
                    vm.pet = response.data;
                }, errorHandler);
        }

        function renderPets(response) {
            vm.pets = response.data;
            vm.pet = {};
        }


        function errorHandler(error) {
            console.log(error);
        }

        function findAllPersons() {
            UserService
                .findAllUsers()
                .then(renderUsers, errorHandler);
        }

        function renderUsers(response) {
            vm.persons = response.data.filter(person => person.role !== 'ADMIN');
        }

        function findAll() {
            findAllTrainers();
            findAllGymLeaders();
            findAllPets();
            findAllPersons()
        }
    }

})();