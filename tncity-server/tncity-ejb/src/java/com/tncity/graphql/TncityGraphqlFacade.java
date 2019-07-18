/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.graphql;

import com.google.gson.Gson;
import com.tncity.facade.entity.PersonaFacade;
import com.tncity.jpa.pojo.Persona;
import com.tncity.util.BeanUtil;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.language.IntValue;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author edwar
 */
public class TncityGraphqlFacade {

    PersonaFacade personFacade = BeanUtil.lookupFacadeBean(PersonaFacade.class);

    private static GraphQL graphQL;
    private final static String schemaPath = TncityGraphqlFacade.class.getClassLoader().getResource("com/tncity/graphql/schema.graphql").getFile();

    private GraphQL initGraphql() {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(new File(schemaPath));
        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .scalar(dateTime)
                .type(newTypeWiring("Query")
                        .dataFetcher("ping", new StaticDataFetcher(ping()))
                        .dataFetcher("listPerson", listPerson)
                        .dataFetcher("findPerson", findPerson)
                ).type(newTypeWiring("Mutation")
                        .dataFetcher("createPerson", createPerson))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        return build;
    }

    private GraphQL getGraphQL() {
        if (graphQL == null) {
            graphQL = initGraphql();
        }
        return graphQL;
    }

    private String ping() {
        return "RealtimeGraphQL is OK ! -> " + new Date().toString();
    }

    private GraphQLScalarType dateTime = new GraphQLScalarType("DateTime", "DataTime scalar", new Coercing() {
        @Override
        public String serialize(Object input) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format((Date) input);
        }

        @Override
        public Object parseValue(Object input) {
            return serialize(input);
        }

        @Override
        public ZonedDateTime parseLiteral(Object input) {
            if (input instanceof StringValue) {
                return ZonedDateTime.parse(((StringValue) input).getValue());
            } else if (input instanceof IntValue) {
                return Instant.ofEpochMilli(((IntValue) input).getValue().longValue()).atZone(ZoneOffset.UTC);
            } else {
                return null;
            }
        }
    });

    public String executeQueryResultJSON(String query, StringBuilder err) {
        try {
            ExecutionResult executionResult = getGraphQL().execute(query);
            //System.out.println("Q:"+query);
            //return executionResult.getData().toString();
            Map<String, Object> toSpecificationResult = executionResult.toSpecification();
            Gson gson = new Gson();
            String json = gson.toJson(toSpecificationResult);
            return json;

        } catch (Exception e) {
            err.append(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    private DataFetcher listPerson = (DataFetcher) (DataFetchingEnvironment env) -> {
        List<Persona> lstP = personFacade.listAllLight("idpersona", "ASC");
        return lstP;
    };

    private DataFetcher findPerson = (DataFetcher) (DataFetchingEnvironment env) -> {
        String numdocumento = env.getArgument("numdocumento").toString();
        Persona p = personFacade.findByNumDocumento(new BigInteger(numdocumento));
        return p;
    };

    private DataFetcher createPerson = (DataFetcher) (DataFetchingEnvironment env) -> {
        String numdocumento = env.getArgument("numdocumento").toString();
        String nombres = env.getArgument("nombres").toString();
        String apellidos = env.getArgument("apellidos").toString();
        String email = env.getArgument("email").toString();
        
        StringBuilder err=new StringBuilder();
        Persona p=new Persona();
        p.setNombres(nombres);
        p.setApellidos(apellidos);
        p.setTipodocumento("CEDULA");
        p.setNumdocumento(new BigInteger(numdocumento));
        p.setEmail(email);
        p.setUpdatedAt(new Date());
        p.setCreatedAt(new Date());
        
      personFacade.create(p, err);

        return "Persona creada!";
    };

    public static void main(String[] args) {
        TncityGraphqlFacade f = new TncityGraphqlFacade();
        StringBuilder err = new StringBuilder();

        String gql = "{ping}";
        //String gql = "query{userByLogin(username:\"admin\"){idusuario,nombre,idcuenta}}";
        //String gql = "query{listMatriculasActivasByCuenta(idcuentart:1){idmatriculart,vehi_matricula}}";

        String res = f.executeQueryResultJSON(gql, err);
        System.out.println("RES:" + res);
        System.out.println("ERR:" + err.toString());
    }
}
