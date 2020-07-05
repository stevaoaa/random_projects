import java.util.Random;

public class Main {

    //sorteia um numero entre -9 e 9
    private static int sorteiaNumero(){

        //obj da lib random
        Random r = new Random();

        //maximo e minimo
        int maximo = 9; 
        int minimo = -9;

        //define o range do sorteio e usa o objeto random pra fazer a seleção
        int range = maximo - minimo + 1;
        int sorteado = r.nextInt(range) + minimo;
        
        return sorteado;
    }

    //metodo princial
    public static void main(String[] args) {

        //cria a lista duplamente encadeada circular
        ListaDuplamenteEncadeadaCircular circular = new ListaDuplamenteEncadeadaCircular();

        //cria os 10 nós na lista, ou seja, 10 soldados
        for (int i = 1; i <= 10; i++) {
            
            //adciona o no na lista circular
            circular.inserirItemNoFinal(i);
        }

        //imprime a lista antes do sorteio começar
        System.out.println("Numero de soldados atualmente: " + circular.getTamanho());
        System.out.println(circular.toString());

        while(circular.getTamanho() > 1){

            //faz o sorteio do numero a ser removido da lista circular
            int sorteado;
            
            do{

                //repete o processo até o numero sorteado ser diferente de zero
                sorteado = sorteiaNumero();

                if (sorteado == 0){
                    System.out.println("O numero 0 foi sorteado. Vamos realizar outro sorteio!");
                }

            }while(sorteado == 0);
            
            System.out.println("Numero sorteado: " + sorteado);
            
            // se for positivo, vai rodar em direção ao proximo nó -> (sentido horario)
            if(sorteado > 0){

                System.out.println("A lista vai rodar no sentido horario!");

                for (int i = 0; i < sorteado -1; i++) {
                    
                    //imprime o soldado da vez
                    System.out.println("Passou pelo soldado: " + circular.getAtual().getId());

                    //move no sentido horario
                    circular.proximoNo();   
                }
            }
            //se for negativo, vai rodar em direção ao nó anterior <- (sentido anti-horario)
            else{

                System.out.println("A lista vai rodar no sentido anti-horario!");

                for (int i = 0; i > sorteado + 1; i--) {
                    //imprime o soldado da vez
                    System.out.println("Passou pelo soldado: " + circular.getAtual().getId());

                    //move no sentido anti-horario
                    circular.voltarNo();
                }
            }

            //soldado a ser removido
            No soldado = circular.atual;

            //remover o item atual
            circular.removeItem();

            //imprime o soldado escolhido
            System.out.println("O soldado removido foi: " + soldado.getId());

            //imprime a lista pra ver como ficou a situação atual
            System.out.println("Numero de soldados atualmente: " + circular.getTamanho());
            System.out.println("Lista atual: " + circular.toString());

            //posiciona o atual como o anterior ao soldado removido para continuar o processo de remoção
            circular.setAtual(soldado.getProximo());
        
        } // sai quando só existe 1 item na lista

        System.out.println("O soldado escolhido foi: " + circular.getAtual().getId());
    } 

    
}