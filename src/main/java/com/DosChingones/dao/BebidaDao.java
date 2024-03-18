/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.dao;

/**
 *
 * @author XPC
 */
import com.DosChingones.domain.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaDao extends JpaRepository<Bebida, Long>{
    
}
