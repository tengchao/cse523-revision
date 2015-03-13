INSERT INTO `cse523_revision`.`people` (`pid`, `firstname`, `lastname`, `email`, `role`, `password`) VALUES ('100000000', 'angel', 'ji', 't1@test.com', 'p', 'ji');
INSERT INTO `cse523_revision`.`people` (`pid`, `firstname`, `lastname`, `email`, `role`, `password`) VALUES ('100000001', 'bob', 'ji', 't2@test.com', 's', 'ji');

INSERT INTO `cse523_revision`.`courses` (`nickid`, `cname`, `seats`, `available_seats`, `semester`) VALUES ('cse110', 'basic java', '30', '30', 'spring 2015');
INSERT INTO `cse523_revision`.`courses` (`nickid`, `cname`, `seats`, `available_seats`, `semester`) VALUES ('cse114', 'basic java II', '30', '30', 'spring 2015');

INSERT INTO `cse523_revision`.`people_courses` (`pid`, `firstname`, `lastname`, `role`, `data`, `cid`, `nickid`, `cname`, `semester`, `section`) VALUES ('100000000', 'angel', 'ji', 'p', '{\"homework\":{\"homework 1\":10,\"homework 2\":10},\"quiz\":{\"quiz 1\":20,\"quiz 2\":20},\"exam\":{\"midterm\":20,\"final\":20}}', '1', 'cse110', 'basic java', 'spring 2015', '0');
INSERT INTO `cse523_revision`.`people_courses` (`pid`, `firstname`, `lastname`, `role`, `data`, `cid`, `nickid`, `cname`, `semester`, `section`) VALUES ('100000000', 'angel', 'ji', 'p', '{\"homework\":{\"homework 1\":10,\"homework 2\":10},\"quiz\":{\"quiz 1\":20,\"quiz 2\":20},\"exam\":{\"midterm\":20,\"final\":20}}', '2', 'cse114', 'basic java II', 'spring 2015', '0');


