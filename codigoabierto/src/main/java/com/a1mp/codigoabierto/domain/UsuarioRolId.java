package com.a1mp.codigoabierto.domain;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioRolId implements Serializable {
    private Long idUsuario;
    private Long idRol;
}