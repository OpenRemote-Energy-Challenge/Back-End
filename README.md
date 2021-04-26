# Openremote-SolarApi:
  
  Calls:
    
    - /solar/getData -> get all data
    - /solar/getData/{id} -> get specific data
    - /solar/pushData -> push data from .csv file (to add test data)
    - /solar/addData + {body} -> add solar data to file

    - /energy/getData -> get all data
    - /energy/getData/{id} -> get specific data
    - /energy/addData + {body} -> add solar data to file
    
 SolarData body => {body} : -> id is autogenerated (ObjectId)
  
  {
    
    "timestamp": "String",
    
    "name": "String",
    
    "attribute_name": "String",
    
    "value": double,
  }   
  
  EnergyData body => {body} : -> id is autogenerated (ObjectId)
  
  {
    
    "timestamp": "String",
    
    "name": "String",
    
    "attribute_name": "String",
    
    "value": double,
  } 
  
  Add Dockercontainer to VPS:
  
  - ssh <ip address> -l <user>
  - git clone https://github.com/OpenRemote-Energy-Challenge/Back-End.git
  - ls /Back-end/demo
  - mvn clean package
  - docker build -t openremote-energy .
  - docker run --ip <ip address> -p 9090:8080 openremote-energy
