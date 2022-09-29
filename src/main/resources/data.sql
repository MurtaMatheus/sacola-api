INSERT INTO RESTAURANTE (ID, CEP, COMPLEMENTO, NOME) VALUES
(1L, '00000001', 'Complemento endereço restaurante 1', 'Restaurante 1'),
(2L, '00000002', 'Complemento endereço restaurante 2', 'Restaurante 2');

INSERT INTO CLIENTE (ID, CEP, COMPLEMENTO, NOME) VALUES
(1L,'00000001', 'Complemento endereço cliente 1', 'Cliente 1');

INSERT INTO PRODUTO (ID, DISPONIVEL, NOME, VALOR_UNITARIO, RESTAURANTE_ID) VALUES
(1L, true , 'Produto 1', 5.0, 1L),
(2L, true , 'Produto 2', 6.0, 1L),
(3L, true , 'Produto 3', 7.0, 2L);

INSERT  INTO  SACOLA (ID, FORMA_PAGAMENTO, FECHADA, VALOR_TOTAL, CLIENTE_ID) VALUES
(1L, 0, true , 0.0, 1L);