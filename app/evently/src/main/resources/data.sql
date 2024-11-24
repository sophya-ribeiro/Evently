-- USUARIOS REGISTRADOS
INSERT INTO usuarios (id, nome, usuario, senha) VALUES
(1, 'Sophya Ribeiro', 'Organizador', '$2a$10$5wsiQ/W2ZlZblhSj8l11hOUSpTpOkcrG9GLE0hEjLaBlHxn65mXLW'),
(2, 'Vanessa Borges', 'Participante', '$2a$10$5wsiQ/W2ZlZblhSj8l11hOUSpTpOkcrG9GLE0hEjLaBlHxn65mXLW');

INSERT INTO organizadores (id, usuario_id, telefone, cnpj, email) VALUES (1, 1, '15461510000133', '67991364257', 'sophya@gmail.com');

INSERT INTO participantes (id, usuario_id) VALUES(1, 2);


-- TABELA LOGS

CREATE TABLE log_eventos (
    id SERIAL PRIMARY KEY,
    evento_id BIGINT NOT NULL,               
    acao VARCHAR(50) NOT NULL,               
    data_hora TIMESTAMP NOT NULL DEFAULT NOW(), 
    motivo TEXT                              
);


-- FUNÇÃO PARA CAHAMADA DO TRIGGER

CREATE OR REPLACE FUNCTION log_eventos_function() 
RETURNS TRIGGER AS $$
BEGIN
    -- Ao atualizar
    IF TG_OP = 'UPDATE' THEN
        INSERT INTO log_eventos (evento_id, acao, data_hora, motivo)
        VALUES (
            NEW.id,                          
            'UPDATE',                        
            NOW(),                          
            'Evento atualizado'            
        );
        RETURN NEW;
    
    -- Ao deletar
    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO log_eventos (evento_id, acao, data_hora, motivo)
        VALUES (
            OLD.id,                          
            'DELETE',                        
            NOW(),                           
            'Evento excluído'                
        );
        RETURN OLD;
    END IF;
    
    RETURN NULL; 
END;
$$ LANGUAGE plpgsql;

-- TRIGGER
CREATE TRIGGER log_eventos_trigger
AFTER UPDATE OR DELETE ON eventos   
FOR EACH ROW
EXECUTE FUNCTION log_eventos_function();

