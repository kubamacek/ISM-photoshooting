package io.swagger.api;

import io.swagger.model.Comment;
import io.swagger.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-29T16:53:00.885Z[GMT]")
@Controller
public class PostsApiController implements PostsApi {

    private static final Logger log = LoggerFactory.getLogger(PostsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private PostsApiService postsApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public PostsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addPost(@ApiParam(value = "Post object that needs to be created" ,required=true )  @Valid @RequestBody Post body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addPostComment(@ApiParam(value = "Comment object that needs to be created" ,required=true )  @Valid @RequestBody Comment body
,@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deletePost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deletePostComment(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("postId") Integer postId
,@Min(1)@ApiParam(value = "Comment id",required=true, allowableValues="") @PathVariable("commentId") Integer commentId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> editPostComment(@ApiParam(value = "Comment object that needs to be updated" ,required=true )  @Valid @RequestBody Comment body
,@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("postId") Integer postId
,@Min(1)@ApiParam(value = "Comment id",required=true, allowableValues="") @PathVariable("commentId") Integer commentId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Post> getPost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Post>(objectMapper.readValue("{\n  \"date\" : \"date\",\n  \"author\" : \"author\",\n  \"description\" : \"description\",\n  \"photo\" : \"photo\",\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"likes\" : 6\n}", Post.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        Post post = postsApiService.getPostbyId(id);
        return new ResponseEntity<Post>(post, HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Comment> getPostComment(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("postId") Integer postId
,@Min(1)@ApiParam(value = "Comment id",required=true, allowableValues="") @PathVariable("commentId") Integer commentId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Comment>(objectMapper.readValue("{\n  \"author\" : \"author\",\n  \"id\" : 0,\n  \"body\" : \"body\"\n}", Comment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Comment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> getPostComments(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Post>> getPosts() {
        String accept = request.getHeader("Accept");
        List<Post> posts = postsApiService.getPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> likePost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updatePost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
,@ApiParam(value = "Post object that needs to be updated"  )  @Valid @RequestBody Post body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
