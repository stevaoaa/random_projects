public class No {
    private No proximo;
    private No anterior;
    private Integer id;

    //esse é o construtor do primeiro nó da lista
    No(Integer id) {
        this.proximo  = null;
        this.anterior = null;
        this.id = id;
        
    }


    //Construtor para todos os demais nós
    No(No anterior, Integer id, No proximo){
        this.anterior = anterior;
        this.proximo = proximo;
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public No getAnterior() {
        return this.anterior;
    }
    
    public void setAnterior(No no) {
        this.anterior = no;
    }

    public No getProximo() {
        return this.proximo;
    }

    public void setProximo(No no) {
        this.proximo = no;
    }

}