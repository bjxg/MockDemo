# MockDemo 用Powermock实现的几种常用的单测场景
* 对本方法中外部对象的方法进行mock（详见：UserServiceImplMockTest.testAddUser()）
* 对本方法所在对象中的其它方法mock（详见：UserServiceImplMockTest.testGetUserByIDs()）
* 对静态方法进行mock（详见：ValidatorTest.testIsBlank()）
* 对构造方法的mock（详见：UserServiceImplMockTest.testGetUserByUsername()）
* 对私有方法的mock（详见：UserServiceImplMockTest.testGetUserByIDs()）
* 对静态代码块的mock（未实现）
* 集成测试中的部分对象的mock（详见：UserServiceImplTest.testAddUserMock()）
