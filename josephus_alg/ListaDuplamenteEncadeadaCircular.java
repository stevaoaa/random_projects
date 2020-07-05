
import java.util.NoSuchElementException;
public class ListaDuplamenteEncadeadaCircular {

    
    protected No inicio;
    protected No fim;
    protected No atual; // atual só serve pra percorrer a lista

    public int contador;
 

    //construtor da lista vazia 
    public ListaDuplamenteEncadeadaCircular(){
        inicio = null;
        fim = null;
        atual = null;
        contador = 0;
    }


    //verifica se a lista esta vazia
    public boolean listaVazia(){
        return inicio == null;
    }

    //retorna o tamanho da lista
    public int getTamanho(){
        return contador;
    }

    //para saber o proximo nó
    public No getProximo(){
        return this.atual.getProximo();
    }

    //para saber o nó anterior
    public No getAnterior(){
        return this.atual.getAnterior();
    }

    //para saber o nó anterior
    public No getAtual(){
        return this.atual;
    }

    //para saber o nó anterior
    public void setAtual(No no){
        this.atual = no;
    }

    //para fazer a lista andar para o proximo nó
    public void proximoNo() {
        atual = atual.getProximo();
    }

    //para fazer a lista andar para o  nó anterior
    public void voltarNo() {
        atual = atual.getAnterior();
    }

    //insere no final da lista
    public void inserirItemNoFinal(int id){

        //cria o obj No a ser inserido
        No novoNo = new No(null, id, null);
        
        //insere dessa forma quando a lista esta vazia
        if (inicio == null){

            //como a lista só tem  1 elemento, ele aponta pra ele mesmo em todas as situações 
            novoNo.setProximo(novoNo);
            novoNo.setAnterior(novoNo);
            inicio = novoNo;
            fim = inicio;
            atual = inicio; // unica vez que inicializa o atual, depois sua posição só é alterada movendo a lista propositalmente
        }

        //insere dessa forma se a lista não estiver vazia
        else
        {

            //novo passa a ser o anterior e o proximo antigo ultimo (pra fazer o loop)
            novoNo.setAnterior(fim);
            fim.setProximo(novoNo);

            //primeiro passa a apontada para o ultimo como o seu anterior (loop)
            inicio.setAnterior(novoNo);

            //novo aponta para o inicio (loop)
            novoNo.setProximo(inicio);

            //atualiza quem é o novo ultimo item da lista
            fim = novoNo;    
        }

        //aumenta o contador
        contador++;
    }


    //remove um item da lista
    public void removeItem() {
        
        //se a lista for vazia não pode remover.. gera exceção
        if (listaVazia())
            throw new NoSuchElementException("Não pode remover da lista vazia");
        
        // atualiza o apontamento do anterior
        No anterior = atual.getAnterior();
        anterior.setProximo(atual.getProximo());

        //atualiza o apontamento do proximo
        No proximo = atual.getProximo();
        proximo.setAnterior(atual.getAnterior());

        //atualiza o inicio eo fim da lista
        inicio =  atual.getProximo();
        fim = atual.getAnterior();

        //reduz o contador de itens
        contador--;
    }

    @Override
    public String toString() {
        
        //pega o primeiro item
        No temp = inicio;

        StringBuilder builder = new StringBuilder("[");
        
        //verifica se a lista só tem 1 item
        if(temp == temp.getProximo()){

            //converte o valor do ID para string
            String index = temp.getId().toString();
            
            //vai montando a lista
            builder.append(index);

            //fecha o colchete da lista
            builder.append("]");
            
            //retorna a string formatada
            return builder.toString();

        }else{ 

            //quando tem mais de 1 item
            while (temp.getProximo() != inicio) {

                //converte o valor do ID para string
                String index = temp.getId().toString();
                
                //vai montando a lista
                builder.append(index);
                builder.append(",");
                
                //vai para o proximo item
                temp = temp.getProximo();
            }

            //ultimo item
            String index = temp.getId().toString();
            builder.append(index);
            
            //fecha o colchete da lista
            builder.append("]");

        }

        //retorna a string formatada
        return builder.toString();
    }

}