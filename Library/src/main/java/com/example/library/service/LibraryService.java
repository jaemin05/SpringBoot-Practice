package com.example.library.service;

import java.util.ArrayList;
import java.util.List;
import com.example.library.Repository.AuthorRepository;
import com.example.library.Repository.BookRepository;
import com.example.library.Repository.LendRepository;
import com.example.library.Repository.MemberRepository;
import com.example.library.model.*;
import com.example.library.model.request.AuthorCreationRequest;
import com.example.library.model.request.BookCreationRequest;
import com.example.library.model.request.BookLendRequest;
import com.example.library.model.request.MemberCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final LendRepository lendRepository;

    public Book readBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            return book.get();
        }
        throw new EntityNotFoundException("Cant find any book under given ID");
    }

    public List<Book> readBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book readBood(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(book.isPresent()){
            return book.get();
        }

        throw new EntityNotFoundException("Cant find any book under given ISBN");
    }

    public Book createBook(BookCreationRequest book) {
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if(!author.isPresent()){
            throw new EntityNotFoundException(
                    "Author Not Found");
        }

        Book bookToCreate = new Book();
        BeanUtils.copyProperties(book, bookToCreate);
        bookToCreate.setAuthor(author.get());
        return bookRepository.save(bookToCreate);

    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public com.example.library.model.Member createMember(MemberRepository request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, MemberCreationRequest request) {
        Optional optionalMember = memberRepository.findById(id);
        if(!optionalMember.isPresent()) {
            throw new EntityNotFoundException(
                    "Member not present in the database");
        }

        Member member = (Member) optionalMember.get();
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());
        return memberRepository.save(member);
    }

    public Author createAuthor(AuthorCreationRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        return authorRepository.save(author);
    }

    public List<String> lendABook (BookLendRequest list) {
        List<String> booksApprovedToBurrow = new ArrayList<>();
        List.forEach(bookLendRequest -> {
            Optional<Book> bookForId = bookLendRequest.findById(bookLendRequest.getBookId());
            if(!bookForId.isPresent()) {
                throw new EntityNotFoundException("Cant find any book under given ID");
            }

            Optional<Member> memberForId = memberRepository.findById(bookLendRequest.getMemberId());
            if(!memberForId.isPresent()) {
                throw new EntityNotFoundException("Member not present in the database");
            }

            Member member = memberForId.get();
            if(member.getStatus() != MemberStatus.ACTIVE) {
                throw new RuntimeException("User is not active to proceed a lending.");
            }

            Optional<Lend> burrowedBook = lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BURROWED);

            if(!burrowedBook.isPresent()) {
                booksApprovedToBurrow.add(bookForId.get().getName());
                Lend lend = new Lend();
                lend.setMember(memberForId.get());
                lend.setBook(bookForId.get());
                lend.setStatus(LendStatus.BURROWED);
                lend.setStartOn(Instant.now());
                lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
                lendRepository.save(lend);
            }
        });
        return booksApprovedToBurrow;
    }





}
