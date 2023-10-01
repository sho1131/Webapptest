package com.example.demo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Integer id;

	//日付
    @Column(name = "date")
    private LocalDate date;
    //開始時刻
    @Column(name = "starthour")
    private String startHour;
    //終了時刻
    @Column(name = "endhour", nullable = false)
    private String endHour;
    //タスク名
	@Column(name = "name", nullable = false)
    private String name;
    //タスク詳細
    @Column(name = "memo", nullable = false)
    private String memo;
}
