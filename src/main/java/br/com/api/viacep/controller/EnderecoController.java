package br.com.api.viacep.controller;

import br.com.api.viacep.model.Endereco;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class EnderecoController {


    @GetMapping("/cep/{cep}")
    public Endereco getCep(@PathVariable String cep) {

        Endereco endereco = new RestTemplate().getForObject("https://viacep.com.br/ws/" + cep + "/json/", Endereco.class);

        return endereco;

    }

    @GetMapping("/ceps/{uf}/{cidade}/{logradouro}")
    public List<Endereco> getCity(@PathVariable String uf, @PathVariable String cidade, @PathVariable String logradouro) {

        String url = "https://viacep.com.br/ws/" + uf + "/" + cidade + "/" + logradouro + "/json/";

        Endereco[] endereco = new RestTemplate().getForEntity(url, Endereco[].class).getBody();

        List<Endereco> list = Arrays.asList(endereco);

        for(Endereco end : list){
            System.out.println(end.getCep());
            System.out.println(end.getLogradouro());
            System.out.println(end.getComplemento());
            System.out.println(end.getBairro());
            System.out.println(end.getLocalidade());
            System.out.println(end.getUf());
            System.out.println(end.getIbge());
            System.out.println(end.getGia());
            System.out.println(end.getDdd());
            System.out.println(end.getSiafi());
            System.out.println("------------------------------");

        }
        return list;
    }
}
