package com.insurtech.review.controllers;

import com.insurtech.review.command.CommentCommand;
import com.insurtech.review.command.VoteCommand;
import com.insurtech.review.domain.Comment;
import com.insurtech.review.domain.Product;
import com.insurtech.review.domain.Vote;
import com.insurtech.review.services.CommentService;
import com.insurtech.review.services.ProductService;
import com.insurtech.review.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final CommentService commentService;
    private final VoteService voteService;

    public ProductController(ProductService productService, CommentService commentService,
                             VoteService voteService) {
        this.productService = productService;
        this.commentService = commentService;
        this.voteService = voteService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product newProduct = new Product(product.isShow(), product.isShowComments(),
                    product.isShowVotes(), product.isSaveComment(), product.isSaveVote());
            productService.addProduct(newProduct);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<Set<Product>> getAllProducts() {
        try {
            Set<Product> products = productService.getProducts();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product oldProduct = productService.getProduct(id);
        if (product != null) {
            oldProduct.setShow(product.isShow());
            oldProduct.setShowComments(product.isShowComments());
            oldProduct.setShowVotes(product.isShowVotes());
            oldProduct.setSaveComment(product.isSaveComment());
            oldProduct.setSaveVote(product.isSaveVote());
            productService.addProduct(oldProduct);
            return new ResponseEntity<>(oldProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody CommentCommand commentCommand) {
        try {
            Comment comment = commentService.saveComment(commentCommand.getProductId(), commentCommand.getUserId(),
                    commentCommand.getValue());
            if (comment != null) {
                return new ResponseEntity<>(comment, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/comments/count")
    public ResponseEntity<Integer> getCommentsCount(@RequestParam(value = "productId") long productId) {
        try {
            int result = commentService.getCommentCount(productId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/comments/top")
    public ResponseEntity<Set<Comment>> getTopComments(@RequestParam(value = "productId") long productId) {
        try {
            Set<Comment> comments= commentService.getTopComments(productId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/votes")
    public ResponseEntity<Vote> createVote(@RequestBody VoteCommand voteCommand) {
        try {
            Vote vote = voteService.saveVote(voteCommand.getProductId(), voteCommand.getUserId(),
                    voteCommand.getValue());
            if (vote != null) {
                return new ResponseEntity<>(vote, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/votes/average")
    public ResponseEntity<Double> getVoteAverage(@RequestParam(value = "productId") long productId) {
        try {
            double result = voteService.getVoteAverage(productId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id,
                                                 @RequestParam(value = "confirmed") boolean confirmed) {
        Comment comment = commentService.updateCommentStatus(id, confirmed);
        if (comment != null) {
            if (comment.isConfirmed() == confirmed) {
                return new ResponseEntity<>(comment, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/votes/{id}")
    public ResponseEntity<Vote> updateVote(@PathVariable("id") long id,
                                           @RequestParam(value = "confirmed") boolean confirmed) {
        Vote vote = voteService.updateVoteStatus(id,confirmed);
        if (vote != null) {
            if (vote.isConfirmed() == confirmed) {
                return new ResponseEntity<>(vote, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
