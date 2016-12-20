# Sample Project with Spring MVC, SAML, Spring Security and Angular 2
Starter project developed using Spring MVC, Angular 2 , Spring security and SAML

## Description

This project is starter project to demonstrate technologies like Spring MVC, SAML, Spring Security and Angular 2.

Once it is complete, we will have full integration with Angular 2 and Public IDP(ssocirecle.com) as
SAML Identity Provider and angular2 app will work as Service provider.

You will need an account on ssocircle.com to run this application. This app is currently tested
on tomcat 8 but should work on other servlet containers too.

Another good thing implemented on this project is maven frontend plugin which is building single
war file including Angular 2 resources served via servlet container

## Technologies Used
Spring MVC 4.1
Spring Security 3.x
Spring Security SAML Extension 1.x
Angular 2 Final(2.2.3 Release)


## How to Run

>   run mvn clean install

>   This should generate war file

>   Deploy generated war to any servlet container like tomcat 8.0.39