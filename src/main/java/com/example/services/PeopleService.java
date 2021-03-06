package com.example.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.example.exceptions.PersonAlreadyExistsException;
import com.example.exceptions.PersonNotFoundException;
import com.example.model.Person;

@Named
public class PeopleService {
	private final ConcurrentMap< String, Person > persons = new ConcurrentHashMap< String, Person >(); 
	
	@PostConstruct
	public void init() {		
		persons.put( "a@b.com", new Person( "a@b.com", "Tom", "Bombadilt" ) );
	}
	
	public Collection< Person > getPeople( int page, int pageSize ) {
		final Collection< Person > slice = new ArrayList< Person >( pageSize );
		
        final Iterator< Person > iterator = persons.values().iterator();
        for( int i = 0; slice.size() < pageSize && iterator.hasNext(); ) {
        	if( ++i > ( ( page - 1 ) * pageSize ) ) {
        		slice.add( iterator.next() );
        	}
        }
		
		return slice;
	}
	
	public Person getByEmail( final String email ) {
		final Person person = persons.get( email );
		
		if( person == null ) {
			throw new PersonNotFoundException( email );
		}
		
		return person;
	}

	public Person addPerson( final String email, final String firstName, final String lastName ) {
		final Person person = new Person( email, firstName, lastName );

		if( persons.putIfAbsent( email, person ) != null ) {
			throw new PersonAlreadyExistsException( email );
		}
		
		return person;
	}
	
	public void removePerson( final String email ) {
		if( persons.remove( email ) == null ) {
			throw new PersonNotFoundException( email );
		}
	}
}
