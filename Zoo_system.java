
import java.util.ArrayList;
import java.util.Scanner;

class Zoo_Function{
    // 기능 1 동물 등록/삭제/조회
    public void addAnimal(ArrayList<Animal> animals, Animal animal){
        animals.add(animal);
        System.out.println("success");
    }
    public void removeAnimal(ArrayList<Animal> animals, Animal animal){
        for(Animal ani : animals){
            if(ani.name.equals(animal.name))
                animals.remove(ani);
        }
    }

    public void addManeger(ArrayList<Maneger> manegers, Maneger maneger){
        manegers.add(maneger);
        System.out.println("success");
    }

    public void removeManeger(ArrayList<Maneger> manegers, Maneger maneger){
        for(Maneger mani : manegers){
            if(mani.name.equals(maneger.name))
                manegers.remove(mani);
        }
    }

    // 기능 2 사육사 등록 - 동물 할당
    public void ManegerMatchAnimal(ArrayList<Animal> animals, ArrayList<Maneger> manegers){
        int PerManger = animals.size()/manegers.size(); // 정수로 딱 떨어진다고 가정함
        int Mane_idx = 0;

        for(int i = 1; i<=animals.size(); i++){
            manegers.get(Mane_idx).required_Animals.add(animals.get(i-1));
            if(i%PerManger == 0 && i != animals.size()) {Mane_idx +=1;} // 사육사별로 지정된 수의 동물 저장
        }
    }

    // 기능 3 관리자별 동물 목록 조회
    public void viewManegerAnimals(ArrayList<Maneger> manegers){
        for(int i = 0; i<manegers.size(); i++){
            for(int j = 0; j< manegers.get(i).required_Animals.size(); j++)
                System.out.println(manegers.get(i).name + manegers.get(i).required_Animals.get(j));
        }
    }
}

class Maneger{
    String name;
    ArrayList<Animal> required_Animals = new ArrayList<>();

    Maneger(String name) {
        this.name = name;
    }
}

class Animal{
    String name;
    String meal;
    String species;

    public Animal(String name, String meal, String species) {
        this.name = name;
        this.meal = meal;
        this.species = species;
    }

    @Override
    public String toString() {
        return " name='" + name + '\'' +
                ", meal='" + meal + '\'' +
                ", species='" + species + '\''
                ;
    }
}

interface carnivore{
    abstract void dailyMeat();
}

interface Herbivore {
    abstract void dailyPlants();
}

class Tiger extends Animal implements carnivore{

    public Tiger(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyMeat(){
        super.meal = "Meat";
    }
}

class Lion extends Animal implements carnivore{

    public Lion(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyMeat(){
        super.meal = "Meat";
    }
}

class Elephant extends Animal implements Herbivore {
    public Elephant(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyPlants(){
        super.meal = "Plants";
    }
}

class Penguin extends Animal implements Herbivore {
    public Penguin(String name, String meal, String species) {
        super(name, meal, species);
    }

    public void dailyPlants(){
        super.meal = "Plants";
    }
}

public class Zoo_system {

    public static void main(String[] args) {
        Zoo_Function function = new Zoo_Function();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Maneger> manegers = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("1 - 동물등록 | 2 - 사육사 등록 | 3 - 동물 정보 보기");

            System.out.print("옵션을 입력해주세요 : ");
            int option = s.nextInt(); s.nextLine();
            if (option == 1) { // 동물 등록
                System.out.print("동물 이름을 입력해 주세요 : ");String name = s.nextLine().trim();
                System.out.print("어떤 동물인가요? : "); String type = s.nextLine().trim();
                animals.add(createAnimal(animals, name, type));
            }
            else if (option == 2) { // 사육사 등록
                System.out.print("사육사 이름을 입력해 주세요 : "); String name = s.nextLine();
                function.addManeger(manegers, new Maneger(name));
            }
            else if (option == 3) { // 동물 정보 보기
                function.ManegerMatchAnimal(animals, manegers);
                function.viewManegerAnimals(manegers);
            }
            else // 종료
                break;
        }

        function.addAnimal(animals, new Elephant("ele1", "Plants", "Herbivore"));
        manegers.add(new Maneger("박찬우"));
    }

    public static Animal createAnimal(ArrayList<Animal> animals,String name, String type){
        Animal newAnimal = null;
        if(type.equalsIgnoreCase("Elephant"))
            newAnimal = new Elephant(name, "Plants", "Herbivore");
        else if(type.equalsIgnoreCase("Penguin"))
            newAnimal = new Penguin(name, "Plants", "Herbivore");
        else if(type.equalsIgnoreCase("Lion"))
            newAnimal = new Lion(name, "Meat", "carnivore");
        else if(type.equalsIgnoreCase("Tiger"))
            newAnimal = new Tiger(name, "Meat", "carnivore");
        return  newAnimal;
    }
}
