# springbot-cars-app

![maluchy east news_7119366_2](https://user-images.githubusercontent.com/98256146/159142578-560da333-932c-4b67-a1bb-e0aeef98c46e.jpg)

teai_pracadomowatydzien3

Adnotacje SpringBoot: 
@RequestMapping, 
@GetMapping("/{id}")    -> getCarsById(@PathVariable...) oraz getCarsByKolor(@RequestParam...) 
@PostMapping            -> addCar(@RequestBody...)
@PutMapping             -> modCarsById(@RequestBody...)
@DeleteMapping("/{id}") -> removeCar (@PathVariable...)
@PatchMapping("/{id}")  -> modifyCarField(@PathVariable int id,  @RequestBody Map<Object, Object> carFields)

Car Api dla Modułu 3 Akadmia Spring
Akademia Spring modol 3 - REST API dla listy pojazdów

Pojazd - pola: id, mark, model, color. API obsługuje metody webowe: 
GET: getCars,   - 

GET: getCarsById, 

GET: getByColor,

POST addCar 

PUT modCarsById

PATCH modifyCarField, 

DELETE: removeCar,

Przy starcie aplikacji mają dodawać się 3 pozycje.
