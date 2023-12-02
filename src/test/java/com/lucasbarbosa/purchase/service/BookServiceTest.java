package com.lucasbarbosa.purchase.service;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/*
If this project adopted the use of autowired annotation instead of constructors,
it would be necessary to do the following changes refering to dependency injection:

@ExtendWith(SpringExtension.class)
class BookServiceTest {
  @Mock private BookRepository repository;
  @InjectMocks private final BookService service = new BookServiceImpl();
  */

/**
 * @author Lucas Barbosa on 27/06/2021
 */
@RunWith(MockitoJUnitRunner.class)
class BookServiceTest extends BookServiceTestSupport {}
