package ninth;

import ninth.part1.MirageMaintenance;
import ninth.part2.MirageMaintenance2;

public class Main {
    public static void main(String[] args) {
        MirageMaintenance mirageMaintenance = new MirageMaintenance("input9.txt");
        System.out.println(mirageMaintenance.getSumOfAllRows());
        //-------------------------------------------------------------------------------------------------
        MirageMaintenance2 mirageMaintenance2 = new MirageMaintenance2("input9.txt");
        System.out.println(mirageMaintenance2.getSumOfAllRows());
    }
}
